name := "quiz"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.5.16",
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.typesafe.slick" %% "slick" % "3.2.3",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.3",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc41",
  "de.heikoseeberger" %% "akka-http-circe" % "1.21.0",
  "io.circe" %% "circe-generic" % "0.10.0",
  "org.scorexfoundation" %% "scrypto" % "2.1.2",
  "com.github.tminglei" %% "slick-pg" % "0.16.2",
  "com.github.tminglei" %% "slick-pg_circe-json" % "0.16.2"
)