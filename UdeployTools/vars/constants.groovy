Package constants

def extFile="comes form extern"

// Methods in this file will end up as object methods on the object that load returns.
def lookAtThat(String whoAreYou) {
    echo "Look at this, ${whoAreYou}! You loaded this from another file!"
}


return this;
