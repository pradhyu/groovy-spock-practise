
def trick=['Hubert', 'Alexander', 'Klein', 'Ikkink'].inject('mr') {
    nickname, name -> nickname + " : " + name
}.toString()
println(trick)