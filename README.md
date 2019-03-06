# Cryptography-Saint-Rose

### Encryption Methods
#### Shift Cipher
Shift letters by a given amount denoted as k.

#### Affine Cipher
Shift letters with multiplicative offset. Alpha is multiplicative offset, and beta is additive offset.

#### RSA
Traditional RSA implementation. You provide p and q, it handles the rest for encrypting text. (p-1)*(q-1) MUST be greater than the greatest possible character value which is stored in the constant BLOCK_MAX_VAL(Java this is 65535). To use you just need to new up a private key, then use the private key to get the public key. Now you can encrypt with the public key, and decrypt with the private key.

### Utilities
#### calcGCD
Uses Euclidean's to calculate the greatest common denominator for the two passed in values.

#### modularExponentiation
Calculates (y^x mod n) using binary string, left to right method.

#### modularInverse
Uses BigInteger to calculate inverse mod. Helper method.

#### chineseRemainderTheorem
Uses the Chinese Remainder Theorem to calculate (a mod m) (b mod n) = (x mod m*n)

#### joinMessage
Accepts int[], char[], or String[] as well as a String separator. Simply concatenates the message together using the separator.
