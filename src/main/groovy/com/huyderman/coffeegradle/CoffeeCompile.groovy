/**
 * 
 */
package com.huyderman.coffeegradle

import org.gradle.api.DefaultTask;
import org.gradle.api.InvalidUserDataException
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SkipWhenEmpty
import org.jcoffeescript.JCoffeeScriptCompiler
import org.jcoffeescript.Option

/**
 * @author Jo-Herman Haugholt <johannes@huyderman.com>
 */
class CoffeeCompile extends DefaultTask {
	@InputFiles
	@SkipWhenEmpty
	FileCollection srcFiles
	
	@OutputDirectory
	File destinationDir = project.file("build/js")
	
	def description = "Compiles CoffeeScript to JavaScript"
	
	boolean bare = false;

	@TaskAction
	public void compile() {
		
		if (srcFiles == null) {
			throw new InvalidUserDataException("srcFiles must be set!");
		}
		
		destinationDir.mkdirs()
		
		def compiler = new org.jcoffeescript.JCoffeeScriptCompiler(bare?[Option.BARE]:Collections.<Option>emptyList())
		
		def count = 0
		srcFiles.each {
			print "Compiling $it"
			
			// Change the extension
			def filetokens = it.name.tokenize(".")
			if(filetokens.size()>1) filetokens.pop()
			filetokens.push("js")
			
			def newFile = new File(destinationDir, filetokens.join("."))
			println " to $newFile..."
			newFile.text = compiler.compile(it.text)
			println "Done!"
			count++ // @todo check files properly compiled
		}
		setDidWork(count)
	}
}
