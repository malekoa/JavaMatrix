#!/bin/sh
if [[ $# -eq 0 ]]; then
    echo "Takes one argument, run or init"
    exit 1
fi

if [[ $1 == "run" ]]; then
    if [[ ! -d "bin" ]]; then
        echo "Error: Project must be initialized before running"
    else
        clear && javac -d bin src/*.java && cd bin && java Main && cd ..
    fi
fi

if [[ $1 == "init" ]]; then

    if [[ ! -d "bin" ]]; then
        chmod +x project.sh
        mkdir bin
        mkdir src
        touch .gitignore
        touch README.md
        touch src/Main.java
        echo "// 
public class Main {
    public static void main(String[] args) {
        // todo
    }
}
    " > src/Main.java
    echo "Done!"
    else
        echo "Project already initialized"
    fi
fi