name := """social-crowd"""

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.5"

assemblyJarName in assembly := "socialcrowd.jar"

mainClass in assembly := Some("com.flurdy.socialcrowd.Launcher")

libraryDependencies ++= Seq(
    "org.specs2"          %%  "specs2-core"     % "2.3.12" % "test",
    "ch.qos.logback"      %   "logback-classic" % "1.1.1",
    "org.slf4j"           %   "slf4j-api"       % "1.7.7"
)
