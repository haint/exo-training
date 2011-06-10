import org.exoplatform.portal.application.PortalController;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletConfig;

import junit.framework.TestCase;

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

/**
 * @author <a href="mailto:haint@exoplatform.com">Nguyen Thanh Hai</a>
 *
 * @datAug 16, 2011
 */
public class TestMockHttp extends TestCase
{
   public void testMockHttpRequest() throws Exception {
      MockHttpServletRequest request = new MockHttpServletRequest();
      PortalController controller = new PortalController();
      MockServletConfig servletConfig = new MockServletConfig();
      controller.init(servletConfig);
   }
}
