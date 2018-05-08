for file in `ls -F $PWD | grep "/$"`
 do
  git update-index --assume-unchanged $file.classpath
  git update-index --assume-unchanged $file.project
  git update-index --assume-unchanged $file.factorypath
  git update-index --assume-unchanged $file.settings/org.eclipse.wst.common.component
  git update-index --assume-unchanged $file.settings/org.eclipse.jdt.core.prefs
  git update-index --assume-unchanged $file.settings/org.eclipse.wst.common.project.facet.core.xml
done