var stream = generate(function() { return "enum"; }).limit(10);

stream.forEach(print); // displays 10 times the word "enum"

var s = enumerate(5, function(e) { return e + 1; }); // creates an infinite stream of numbers from 6 (5 + 1) iterating
s = s.filter(function(e) { var r = (e % 2) == 0; return r; }); // deletes all even numbers (true and false are not recognized by the grammar, so we use a trick here)
s = s.limit(100); // cuts the stream to stop after the hundredth element
s = s.skip(90); // skips the 90 first elements

//print(s.count()); // displays 10 (100 - 90)
s.forEach(print); // displays even numbers from 186 (6 + 90 * 2) to 204 (6 + 99 * 2)
print(s.count()); // displays 0, the stream is consumed!

print(generate(function() { return 1; }).map(function(a) { return a + 4; } ).limit(10).skip(1).findFirst());
// displays 5, the first element of our stream of 9 elements (10 -1), each elements being equal to 5 (1 + 4)
