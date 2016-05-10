import org.jboss.as.cli.scriptsupport.*

// Arguments length test
if (args.length != 5) {
    println "Error: wrong arguments length."
    System.exit(1)
}

// Load parameters for application deploy: filePath, appName, appRuntimeName, serverGroup 
def filePath = args[0]
def appName = args[1]
def appRuntimeName = args[2]
def serverGroup = args[3]
def deployMode = args[4]

// New cli instance
cli = CLI.newInstance()

// Use this connect method for local
cli.connect()            // INFO messages are produced to stderr

// Use these connect method for remoting
// cli.connect(String controllerHost, int controllerPort, String username, char[] password)
// The password field is a Char Array and must be converted from string
// Example: cli.connect("myhost", 9999, "admin", "mypass".toCharArray()

// Artifact deployment
if (deployMode.toString() == "rollout") {
  result = cli.cmd("deploy " + filePath + " --name=" + appName + " --runtime-name=" + appRuntimeName + " --headers={rollout " + serverGroup + "(rolling-to-servers=true)} --force")
} else if (deployMode.toString() == "simple") {
  result = cli.cmd("deploy " + filePath + " --name=" + appName + " --runtime-name=" + appRuntimeName + " --server-groups=" + serverGroup)
} else {
  println "Error: invalid deploy mode argument"
  System.exit(2)
}

// Define and print outcome string for deploy.
response = result.getResponse()
deployOutcome = response.get().asString()
println deployOutcome

// Close connection
cli.disconnect()


