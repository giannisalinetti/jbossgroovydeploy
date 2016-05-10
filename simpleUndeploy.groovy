import org.jboss.as.cli.scriptsupport.*

// Arguments length test
if (args.length != 2) {
    println "Error: wrong arguments length."
    System.exit(1)
}

// Load parameters for application undeploy: appName, serverGroup 
def appName = args[0]
def serverGroup = args[1]

// New cli instance
cli = CLI.newInstance()

// Use this connect method for local
cli.connect()            // INFO messages are produced to stderr

// Use these connect method for remoting
// cli.connect(String controllerHost, int controllerPort, String username, char[] password)
// The password field is a Char Array and must be converted from string
// Example: cli.connect("myhost", 9999, "admin", "mypass".toCharArray()

// Artifact 
  result = cli.cmd("undeploy " + appName + " --server-groups=" + serverGroup)

// Define and print outcome string for deploy.
response = result.getResponse()
deployOutcome = response.get().toJSONString(false)
println deployOutcome

// Close connection
cli.disconnect()


