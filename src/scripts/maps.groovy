// Sorting a Map


def mp = [sort: 'asc',name: 'test', paginate: true, max: 100]

def expectedKeys= ['max','name','paginate','sort']

println "mp.sort() ${mp.sort()}"
// using comparator function
println mp.sort({item1,item2 -> item1<=>item2} as Comparator)*.key
// using closure to sort
println mp.sort {item1,item2 -> item1.key<=>item2.key}*.key
println mp.sort().getClass().name
def var = println(mp*.key)
println([1,2,3]*10)
println([1,2,3]*.plus(100))
println([1,2,3]*.plus(100))
println([1,2,3].plus(100))

assert expectedKeys == mp.sort()*.key

// Complex Keys in Maps
def key = 100  // Variable to be used a key.
def m = [
        (new Date(109, 11, 1)): 'date key',
        (-42): 'negative number key',
        (false): 'boolean key',
        (key): 'variable key'
]
m.(true) = 'boolean key'  // Key is converted to String.
m.(2 + 2) = 'number key'
m[(key + 1)] = 'number key'  // Key keeps to be Integer.
assert 'date key' == m[new Date(109, 11, 1)]
assert 'negative number key' == m.get(-42)
assert 'boolean key' == m[(false)]
assert 'variable key' == m[100]
assert 'variable key' == m.getAt(key)
// Key is String so we can use it to get the value.
assert 'boolean key' == m['true']
assert 'number key' == m.'4'
assert 'number key' == m.get(101)


// intersect map
assert m.intersect(mp) == [:]
assert m - mp ==m
assert mp-m==mp
assert (m - m) == [1:2]