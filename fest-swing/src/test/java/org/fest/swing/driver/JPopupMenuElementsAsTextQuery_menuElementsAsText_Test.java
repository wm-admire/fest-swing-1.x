/*
 * Created on Aug 25, 2009
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Arrays.array;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link JPopupMenuElementsAsTextQuery#menuElementsAsText(javax.swing.JPopupMenu)}.
 * 
 * @author Alex Ruiz
 */
public class JPopupMenuElementsAsTextQuery_menuElementsAsText_Test extends EDTSafeTestCase {
  private JPopupMenu popupMenu;

  @Before
  public void setUp() {
    popupMenu = popupMenu();
  }

  @RunsInEDT
  private static JPopupMenu popupMenu() {
    return execute(new GuiQuery<JPopupMenu>() {
      @Override
      protected JPopupMenu executeInEDT() {
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(new JMenuItem("Anakin"));
        popupMenu.addSeparator();
        popupMenu.add(new JMenuItem("Luke"));
        popupMenu.add(new JMenuItem("Leia"));
        return popupMenu;
      }
    });
  }

  @Test
  public void should_return_elements_as_text() {
    String[] elements = JPopupMenuElementsAsTextQuery.menuElementsAsText(popupMenu);
    assertThat(elements).isEqualTo(array("Anakin", "Luke", "Leia"));
  }
}
