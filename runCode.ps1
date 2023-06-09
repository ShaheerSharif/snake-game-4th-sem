# Set the paths
$libFolderPath = "./lib"
$binFolderPath = "./bin"

# Compile the Java code
# javac -cp "$libFolderPath\*" -sourcepath $srcFolderPath -d $srcFolderPath $srcFolderPath\main\Main.java

# Execute the Java program
java -cp "$libFolderPath\*;$binFolderPath" main.Main
