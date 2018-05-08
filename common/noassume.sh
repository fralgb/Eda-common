for file in `ls -F $PWD | grep "/$"`
 do
  git update-index --no-assume-unchanged $file.classpath
  git update-index --no-assume-unchanged $file.project
  git update-index --no-assume-unchanged $file.factorypath
  git update-index --no-assume-unchanged $file.settings/org.eclipse.wst.common.component
  git update-index --no-assume-unchanged $file.settings/org.eclipse.jdt.core.prefs
  git update-index --no-assume-unchanged $file.settings/org.eclipse.wst.common.project.facet.core.xml
done