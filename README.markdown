# CoffeeGradle

This library includes Gradle tasks to compile *[CoffeeScript]* to *JavaScript*.

## Usage

The easiest way to use this libary is to install it to a local Maven repository.
Check out the git repository using:

    git clone git://github.com/huyderman/coffeegradle.git
    cd coffeegradle
    gradle install

You can then use the CoffeeCompile task in your `build.gradle` file like this:

    import com.huyderman.coffeegradle.CoffeeCompile

    buildscript {
        repositories {
            mavenLocal()
        }

        dependencies {
            classpath "com.huyderman:coffeegradle:1.0-ALPHA"
        }
    }

    task compile(type: CoffeeCompile) {
        srcFiles       = fileTree(dir: "src/test/coffee", include: "*.coffee")
        destinationDir = file("build/js")
    }

--------------------------------------------------------------------------------

[CoffeeScript]: http://jashkenas.github.com/coffee-script/
