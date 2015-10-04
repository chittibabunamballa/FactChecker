cd ../code
set CLASSPATH=.;../lib/common-logging.jar;../lib/junit-4.1.jar;../lib/jwnl.jar;../lib/montytagger.jar;%CLASSPATH%
java -Dhttp.proxyHost=172.16.5.2 -Dhttp.proxyPort=80   FactChecker