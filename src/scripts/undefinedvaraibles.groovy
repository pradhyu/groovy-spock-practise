
def bindingVar (varName) {
    def optVar = binding.variables.get(varName)
    if (optVar) {
        println(optVar)
    }
    optVar
}
def localVar="localVariable"
bindingVar("localVar")
globalVar="global variable"
bindingVar("globalVar")

