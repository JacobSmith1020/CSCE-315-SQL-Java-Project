package banana;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static guru.nidi.graphviz.model.Factory.*;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;


public class GraphTest {
	
	public void RenderSchema() throws IOException
	{
		
		try (InputStream dot = GraphTest.class.getResourceAsStream("schema.dot")) {
		    MutableGraph g = new Parser().read(dot);
		    Graphviz.fromGraph(g).width(1920).height(1000).render(Format.PNG).toFile(new File("example/exampleSchema.png"));
		    System.out.println("success");
		}
		catch(Exception e)
		{
			System.out.println("something went wrong.");
		}
		
		
		/*
		MutableGraph g = mutGraph("example1").setDirected(true).add(
		        mutNode("a").add(Color.RED).addLink(mutNode("b")));
		Graphviz.fromGraph(g).width(200).render(Format.PNG).toFile(new File("example/ex1m.png"));
		*/
	}
}
