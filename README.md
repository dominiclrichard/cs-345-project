<h1>CSC 345 Project</h1>
<h2>Huffman Encoding and Decoding (Java)</h2>
<h4>Authors: Andrew More, Ansel Newman, and Dominic Richard</h4>

This Java code implements Huffman encoding, a lossless compression algorithm. It begins by creating a frequency table to count the occurrences of each character in the input text. The Huffman tree is constructed from the characters and their frequencies using a min-heap, where lower frequency characters are prioritized. Huffman codes are generated for each character by traversing the tree, with '0' representing left branches and '1' representing right branches. Finally, the input text is encoded using the Huffman codes, and it can be efficiently decoded back to its original form using the Huffman tree.

<h2>How to Use</h2>

Once the model folder is within your desired directorry, you can declare a Huffman object as follows:
<code>Huffman myHuffObj = new Huffman();</code>

<h4>Encoding</h4>
To encode one can use the encode method as seen here where the parameter is the desired string one wants to encode.
<code>
String givenString = "...";
myHuffObj.encode(givenString);
</code>
This will return a binary string of the encoded product where the code is based off the frequencies associated with the input string.

Similarly to read and encode the contents of a text file one can use the encodeFile method which takes a String file name as its parameter
<code>
String filename = "...";
myHuffObj.encodeFile(filename);
</code>
This will return a binary string of the encoded product where the code is based off the frequencies associated with the input file.

<h4>Decoding</h4>
<b>* An important condsideration when decoding is that this method will only be able to decode if the message was constructed from the same Huffman object used to encode it because that object and its component frequency tables and min heap are based off of the frequencies of the input text.</b>
<p></p>


To decode one can use the decode method as seen here where the parameter is the desired string one wants to decode:
<code>
String givenString = "...";
myHuffObj.decode(givenString);
</code>

Similarly to read and decode the contents of a text file one can use the decodeFile method which takes a String file name as its parameter
<code>
String filename = "...";
myHuffObj.DecodeFile(filename);
</code>
This will return a binary string of the encoded product where the code is based off the frequencies associated with the input file.
