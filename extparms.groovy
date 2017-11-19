
env.extFile="comes form extern"

extFile2="also from extern"

String extFile2(){
return extFile2
}

// Methods in this file will end up as object methods on the object that load returns.
def lookAtThis(String whoAreYou) {
    echo "Look at this, ${whoAreYou}! You loaded this from another file!"
}


return this;
