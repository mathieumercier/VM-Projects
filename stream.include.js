// function undef(){ return (function(){})(); }

//function stream_forEach(fun) {
//    while(this._hasnext()) {
//        fun(this._next())
//    }
//}

//function stream_filter_advance() {
//    var next_tmp = undef();
//    var pred_tmp = undef();
//    while(this.base._hasnext()) {
//        next_tmp = this.base._next();
//        pred_tmp = this.pred(next_tmp);
//        if(pred_tmp) {
//            this.hasnext_tmp = 1 > 0;
//            this.next_tmp = next_tmp;
//            return undef();
//        } else {
//        }
//    }
//    this.next_tmp = undef();
//    this.hasnext_tmp = 1 < 0;
//}

//function stream_filter_hasnext() {
//    if(this.hasnext_tmp == undef()) {
//        this.advance();
//    } else {
//    }
//    return this.hasnext_tmp;
//}

//function stream_filter_next() {
//    var ret = undef();
//    if(this.hasnext_tmp == undef()) {
//        this.advance();
//    } else {
//    }
//    this.hasnext_tmp = undef();
//    ret = this.next_tmp;
//    this.next_tmp = undef();
//    return ret;
//}

//function stream_filter(pred) {
//    return {
//base: this,
//          pred: pred,
//          hasnext_tmp: undef(),
//          next_tmp: undef(),
//          advance: stream_filter_advance,
//          _hasnext: stream_filter_hasnext,
//          _next: stream_filter_next,
//          forEach: stream_forEach,
//          filter: stream_filter,
//          limit: stream_limit,
//          skip: stream_skip
//    };
//}

//function stream_limit_hasnext() {
//    if(this.k < this.n) {
//        return this.base._hasnext();
//    } else {
//        return 1 < 0;
//    }
//}

//function stream_limit_next() {
//    if(this.k < this.n) {
//        this.k = this.k + 1;
//        return this.base._next();
//    } else {
//        return undef();
//    }
//}

//function stream_limit(n) {
//    return {
//base: this,
//          n: n,
//          k: 0,
//          _hasnext: stream_limit_hasnext,
//          _next: stream_limit_next,
//          forEach: stream_forEach,
//          filter: stream_filter,
//          limit: stream_limit,
//          skip: stream_skip
//    };
//}

//function stream_skip_do_skip() {
//    if(this.skipped) {
//    } else {
//        var k = 0;
//        while(k < this.n) {
//            this.base._next();
//            k = k + 1;
//        }
//        this.skipped = 1 > 0;
//    }
//}

//function stream_skip_hasnext() {
//    this.do_skip();
//
//    return this.base._hasnext();
//}

//function stream_skip_next() {
//    this.do_skip();
//
//    return this.base._next();
//}

//function stream_skip(n) {
//    return {
//base: this,
//          n: n,
//          skipped: 1 < 0,
//          do_skip: stream_skip_do_skip,
//          _hasnext: stream_skip_hasnext,
//          _next: stream_skip_next,
//          forEach: stream_forEach,
//          filter: stream_filter,
//          limit: stream_limit,
//          skip: stream_skip
//    };
//}

//function generate_hasnext() {
//    return 1 > 0;
//}

//function generate_next() {
//    return this.gen();
//}

//function generate(gen) {
//    return {
//      gen: gen,
//         _hasnext: generate_hasnext,
//         _next: generate_next,
//         forEach: stream_forEach,
//         filter: stream_filter,
//         limit: stream_limit,
//         skip: stream_skip
//    };
//}

//function enumerate_hasnext() {
//    return 1 > 0;
//}

//function enumerate_next() {
//    var next = this.gen(this.seed);
//    this.seed = next;
//    return next;
//}

//function enumerate(seed, gen) {
//    return {
//seed: seed,
//          gen: gen,
//          _hasnext: enumerate_hasnext,
//          _next: enumerate_next,
//          filter: stream_filter,
//          forEach: stream_forEach,
//          limit: stream_limit,
//          skip: stream_skip
//    };
//}

//function stream_map_hasnext() {
//    return this.base._hasnext();
//}

//function stream_map_next() {
//    return this.mapper(this.base._next());
//}

//function stream_map(mapper) {
//    return {
//        base: this,
//        mapper: mapper,
//        _hasnext: stream_map_hasnext,
//        _next: stream_map_next,
//        filter: stream_filter,
//        forEach: stream_forEach,
//        limit: stream_limit,
//        skip: stream_skip
//    };
//}

// function stream_count() {
//     var counter = 0;
//
//     while(this._hasnext()) {
//         counter = counter + 1;
//         this._next();
//     }
//
//     return counter;
// }

// function stream_find_first() {
//     return this._next();
// }

var s = enumerate(5, function(e) { return e + 1; });
s = s.filter(function(e) { var r = (e % 2) == 0; return r; });
s = s.limit(100);
s = s.skip(90);
print(s.count());

print(generate(function() { return "hello"; }).limit(10).skip(1).findFirst());
