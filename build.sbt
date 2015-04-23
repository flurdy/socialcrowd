name := """social-crowd"""

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.5"

assemblyJarName in assembly := "socialcrowd.jar"

mainClass in assembly := Some("com.flurdy.socialcrowd.Launcher")

libraryDependencies ++= Seq(
    "org.specs2"     %% "specs2"          % "2.4.1" % "test",
    "org.mockito"    %  "mockito-core"    % "1.9.5"  % "test",
    "ch.qos.logback" %  "logback-classic" % "1.1.1", 
    "joda-time"      %  "joda-time"       % "2.3",    
    "org.joda"       %  "joda-convert"    % "1.5",
    "org.slf4j"      %  "slf4j-api"       % "1.7.7"
)
