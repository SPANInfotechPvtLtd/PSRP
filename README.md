# PSRP
#========================================================================================================
# To encrypt configuration properties, perform the following steps:
# 1. Download the Camel binary distribution from the Apache Camel website (http://camel.apache.org/download.html) and extract it into a
# working directory.
# 2. Run the following command in the lib directory of the location that you just extracted Camel into:

# java -jar camel-jasypt-2.12.2.jar -c encrypt -p <encryptionPassword> -i <myPassword>

# The -c flag denotes the task to be performed (encrypt/decrypt). The -p argument is
# the password used to encrypt the property that you wish to hide. The -i argument is
# the input that is to be encrypted

# Copy this password and add it to a properties file that provides placeholders to your
# Camel routes. The encrypted text must be surrounded by an ENC(..) string to denote encryption
# =======================================================================================================
# how to create shared.jceks
# For the purposes of this example we will use the keytool utility that is included as part of the
# Java Development Kit (JDK) to generate the keystore.
# Generate a key into a new store that will be shared between the encrypting and decrypting systems as follows:

# keytool -genseckey -alias shared -keypass sharedKeyPassword -keystore shared.jceks -storepass sharedKeystorePassword -v -storetype JCEKS

# Give this keystore to the people maintaining the system that will be the counterparty to the
# encryption process. Both systems involved in the encryption/decryption process need access to the same key.
#=========================================================================================================
# Digitally signing and verifying messages
#For the purposes of this example, we will use the keytool utility that is included as part of
#the Java Development Kit (JDK):
# 1. Generate a pair of public and private key for the sending code/system (in this example, known by the alias system_a). 
# This will automatically produce a keystore,as follows:
# keytool -genkeypair -v -alias system_a -keystore keystore.jks -validity 3650 -dname 'CN=Scott,O=camelbookbook.org' -storepass keystorePassword -keypass keyPasswordA
# 2. Export the public key certificate:
# keytool -export -alias system_a -keystore keystore.jks -storepass keystorePassword -rfc -file selfsignedcert_a.cer
# 3. Import the public key into a truststore for use by the receiving code/system:
# keytool -import -noprompt -alias system_a -file selfsignedcert_a.cer -keystore truststore.jks -storepass truststorePassword
# 4. Repeat steps 1-3 for an alias of system_b and a certificate file selfsignedcert_b.cer.