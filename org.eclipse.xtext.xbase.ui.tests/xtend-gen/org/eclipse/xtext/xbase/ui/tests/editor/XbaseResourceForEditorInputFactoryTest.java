package org.eclipse.xtext.xbase.ui.tests.editor;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.ui.editor.XbaseResourceForEditorInputFactory;
import org.eclipse.xtext.xbase.ui.tests.AbstractXbaseUITestCase;
import org.junit.Assert;
import org.junit.Test;

@SuppressWarnings("all")
public class XbaseResourceForEditorInputFactoryTest extends AbstractXbaseUITestCase {
  @Inject
  private XbaseResourceForEditorInputFactory editorInputFactory;
  
  @Test
  public void testValidationIsDisabled_01() {
    try {
      final IProject project = this.workspace.getRoot().getProject("simpleProject");
      project.create(null);
      project.open(null);
      final IFile file = project.getFile("Hello.xtext");
      final InputStream _function = new InputStream() {
        @Override
        public int read() throws IOException {
          return (-1);
        }
      };
      file.create(_function, true, null);
      Assert.assertTrue(this.isValidationDisabled(file));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testValidationIsDisabled_02() {
    try {
      final IProject project = AbstractXbaseUITestCase.createPluginProject("my.plugin.project");
      final IFile file = project.getFile("Hello.xtext");
      final InputStream _function = new InputStream() {
        @Override
        public int read() throws IOException {
          return (-1);
        }
      };
      file.create(_function, true, null);
      Assert.assertFalse(this.isValidationDisabled(file));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testValidationIsDisabled_03() {
    try {
      final IProject project = AbstractXbaseUITestCase.createPluginProject("my.plugin.project");
      final IJavaProject jp = JavaCore.create(project);
      boolean wasTested = false;
      IPackageFragmentRoot[] _allPackageFragmentRoots = jp.getAllPackageFragmentRoots();
      for (final IPackageFragmentRoot pfr : _allPackageFragmentRoots) {
        boolean _isArchive = pfr.isArchive();
        if (_isArchive) {
          Iterable<IStorage> _filter = Iterables.<IStorage>filter(((Iterable<?>)Conversions.doWrapArray(pfr.getNonJavaResources())), IStorage.class);
          for (final IStorage r : _filter) {
            {
              Assert.assertTrue(this.isValidationDisabled(r));
              wasTested = true;
            }
          }
        }
      }
      Assert.assertTrue(wasTested);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public boolean isValidationDisabled(final IStorage storage) {
    try {
      final Method method = this.editorInputFactory.getClass().getDeclaredMethod("isValidationDisabled", IStorage.class);
      method.setAccessible(true);
      Object _invoke = method.invoke(this.editorInputFactory, storage);
      return (((Boolean) _invoke)).booleanValue();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
