# Save programming

## Hashing

For encrypting data in Java, we can use the `MessageDigest` class. For instantiating this class, we don't use the constructor, but a `factory method` instead.

Example:
```java
MessageDigest md = MessageDigest.getInstance("MD5");
```

Once we've defined the algorithm we are going to use in the `MessageDigest` instance, we can hash any message (String).

For doing this, we first have to update the MessageDigest instance to the proper number of bytes that the text to hash has.

After all this, we can just hash the message and store it in a bytes array. Full example:
```java
System.out.print("Enter your text: ");
String text = kb.nextLine();

MessageDigest md = MessageDigest.getInstance("MD5");

md.update(text.getBytes());
byte[] hashInBytes = md.digest();

System.out.println("The MD5 hash of '" + text + "' is: " + new String(hashInBytes));
System.out.println("Bytes number: " + md.getDigestLength());
```

## Digital Signature

For creating a digital signature, we need both a public and a private key. For doing this, we use the `KeyPairGenerator` and another class prepared for random algorithms: e.g. `SecureRandom`.

Example of integration on how to create the `KeyPair`:

```java
 KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
keyGen.initialize(1024, random);

KeyPair pair = keyGen.generateKeyPair();
PrivateKey privateKey = pair.getPrivate();
PublicKey publicKey = pair.getPublic();
```

Once we have defined the keys, we have to continue with the signature of the data. This process is similar to the one we've already seen at hashing.
First of all, we have to instantiate the `Signature` class using its builder method, then continue with the initialization of the signature and finally updating and signing the data:

```java
Signature signature = Signature.getInstance("SHA1withDSA");
signature.initSign(privateKey);
signature.update(data);

byte[] digitalSignature = signature.sign();
```

The last step for the digital signature is to check if it's valid or not. This is also done with the `Signature` class.
One thing to take in account is to make sure to use the public key when verifying the signature, not the private key (wouldn't make sense in real world signatures)

Example of implementation:

```java
Signature verifier = Signature.getInstance("SHA1withDSA");
verifier.initVerify(publicKey);
verifier.update(data);

boolean correcta = verifier.verify(digitalSignature);
```