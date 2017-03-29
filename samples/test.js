//var a = 4;
//
//var o = {
//    x: 1,
//    y: 1 + a,
//    t: function() {
//        return this.x + this.y + 2;
//    },
//    print: function () {
//        print(this.x + this.y);
//    }
//};
//
//print(o.x);
//print(o.y);
//o.print();
//print(o.x + o.y);
//print(o.t());

//function plop() {
//    return { a: 1, b: 2 };
//}
//
//plop();
//
//var x = { c: 3, d: plop() };
//print(x);
//
//plop();
//
//var y = { e: 4, f: 5 };
//print(y);

var i = 0;

//var w = { e: 1, f: 2 };
//var x = { e: 3, f: 4 };
//var y = { f: 5, e: 5 };
//
//var z = w;
//
//while(i != 10) {
//    print(z.e);
//    i = i + 1;
//    if (i == 5) {
//        z = x;
//    } else {
//        if (i == 8) {
//            z = y;
//        } else {
//        }
//    }
//}

//function plop(a) {
//    return a;
//}
//
//while(i != 10) {
//    print(plop(i));
//    i = i + 1;
//}

function foo(bar) {
    bar();
}

function baz() {
    print('baz');
}

function bar() {
    print('bar');
}

foo(baz);
foo(baz);
foo(bar);
foo(baz);

print(1 + 2);
