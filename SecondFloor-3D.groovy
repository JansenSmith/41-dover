//Based on http://localhost:60408/JavaCAD/SVGExtrude/index.html


import eu.mihosoft.vrl.v3d.svg.*;

import com.neuronrobotics.bowlerstudio.scripting.ScriptingEngine

import eu.mihosoft.vrl.v3d.CSG
import eu.mihosoft.vrl.v3d.Extrude;
import eu.mihosoft.vrl.v3d.Polygon

File f = ScriptingEngine
	.fileFromGit(
		"https://github.com/JansenSmith/41-dover.git",//git repo URL
		"master",//branch
		"SecondFloor.SVG"// File from within the Git repo
	)
println "Extruding SVG "+f.getAbsolutePath()
SVGLoad s = new SVGLoad(f.toURI())
println "Layers= "+s.getLayers()
// A map of layers to polygons
HashMap<String,List<Polygon>> polygonsByLayer = s.toPolygons()
// extrude all layers to a map to 10mm thick
HashMap<String,ArrayList<CSG>> csgByLayers = s.extrudeLayers(10)

// extrude just one layer to 10mm
def room1 = s.extrudeLayerToCSG(10,"Lvng Rm")
def room2 = s.extrudeLayerToCSG(12,"Front Bdrm")

/*
// seperate holes and outsides using layers to differentiate
def outsideParts = s.extrudeLayerToCSG(10,"2-outsides")
					.difference(holeParts)
// layers can be extruded at different depths					
def boarderParts = s.extrudeLayerToCSG(5,"3-boarder")
*/
//return [CSG.unionAll([boarderParts,outsideParts]),s.extrudeLayerToCSG(2,"4-star")]