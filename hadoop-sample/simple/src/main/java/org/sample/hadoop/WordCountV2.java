/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.sample.hadoop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @author <a href="mailto:haint@exoplatform.com">Nguyen Thanh Hai</a>
 *
 * @datAug 31, 2011
 */
public class WordCountV2 extends Configured implements Tool
{
   public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
      static enum COUNTERS { INPUT_WORDS }
      
      final private static IntWritable one = new IntWritable(1);
      private Text word = new Text();
      
      private boolean caseSensitive = true;
      private Set<String> patternsToSkip = new HashSet<String>();
      
      private String inputFile;
      
      public void setup(Context context) throws IOException, InterruptedException {
         Configuration conf = context.getConfiguration() ;
         caseSensitive = conf.getBoolean("wordcount.case.sensitive", true);
         String inputFile = conf.get("map.input.file");
         if(conf.getBoolean("wordcount.skip.patterns", false)) {
            Path[] patternsFiles = new Path[0];
            try {
               patternsFiles = DistributedCache.getLocalCacheArchives(conf);
            } catch(IOException e) {
               System.err.println("Caught exception while getting cached files: " + StringUtils.stringifyException(e));
            }
         }
      }
      
      private void parseSkipFile(Path patternsFile) {
         try
         {
            BufferedReader fis = new BufferedReader(new FileReader(patternsFile.toString()));
            String pattern = null;
            while((pattern = fis.readLine()) != null) {
               patternsToSkip.add(pattern);
            }
         }
         catch (IOException e)
         {
            System.err.println("Caught exception while getting cached files: " + StringUtils.stringifyException(e));
         }
      }
      
      public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
         String line = (caseSensitive) ? value.toString() : value.toString().toLowerCase();
         for(String pattern : patternsToSkip) {
            line = line.replaceAll(pattern, "");
         }
         
         StringTokenizer tokenizer = new StringTokenizer(line);
         while(tokenizer.hasMoreTokens()) {
            word.set(tokenizer.nextToken());
            context.write(word, one);
         }
         context.getCounter(COUNTERS.INPUT_WORDS).increment(1);
      }
   }
   
   public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
      
      public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
         int sum = 0;
         for(IntWritable value : values) {
            sum += value.get();
         }
         context.write(key, new IntWritable(sum));
      }
   }

   public int run(String[] args) throws Exception
   {
      Configuration conf = getConf();
      Job job = new Job(conf);
      job.setJobName("wordcount");
      job.setJarByClass(this.getClass());
      job.setOutputKeyClass(Text.class);
      job.setOutputValueClass(IntWritable.class);
      job.setCombinerClass(IntSumReducer.class);
      job.setMapperClass(TokenizerMapper.class);
      job.setInputFormatClass(TextInputFormat.class);
      job.setOutputFormatClass(TextOutputFormat.class);
      List<String> other_args = new ArrayList<String>();
      for(int i = 0; i < args.length; i++) {
         if("-skip".equals(args[i])) {
            DistributedCache.addCacheFile(new Path(args[++i]).toUri(), job.getConfiguration());
            job.getConfiguration().setBoolean("wordcount.skip.patterns", true);
         } else {
            other_args.add(args[i]);
         }
      }
      FileInputFormat.addInputPath(job, new Path(other_args.get(0)));
      FileOutputFormat.setOutputPath(job, new Path(other_args.get(1)));
      job.submit();
      return 0;
   }
   
   public static void main(String[] args) throws Exception
   {
      int res = ToolRunner.run(new Configuration(), new WordCountV2(), args);
      System.exit(res);
   }
}