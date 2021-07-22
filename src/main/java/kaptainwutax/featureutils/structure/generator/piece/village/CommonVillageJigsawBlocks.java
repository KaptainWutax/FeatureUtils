package kaptainwutax.featureutils.structure.generator.piece.village;

import kaptainwutax.mcutils.util.data.Pair;
import kaptainwutax.mcutils.util.data.Quad;
import kaptainwutax.mcutils.util.pos.BPos;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class CommonVillageJigsawBlocks {
	//import nbtlib
	//from pathlib import *
	//import sys
	//from typing import *
	//
	//p = Path(r'.').glob('**/*.nbt')
	//files = [x for x in p if x.is_file()]
	//for file in files:
	//    nbt_file=nbtlib.load(file)
	//    root=nbt_file.root
	//    parents=str(file.parents[0]).replace("\\","/")
	//    print(f'this.put("{parents}/{file.name.rstrip(".nbt")}", ',end="")
	//    if "blocks" not in root.keys():
	//        print(f"Missing blocks key for {file}")
	//        sys.exit(1)
	//    if "palette" not in root.keys():
	//        print(f"Missing palette key for {file}")
	//        sys.exit(1)
	//    palette=root["palette"]
	//    l=[]
	//    for i,p in enumerate(root["blocks"]):
	//        if "nbt" in p.keys() and "pos" in p.keys():
	//            nbt=p["nbt"]
	//            pos:List[int]=p["pos"]
	//            state:int=p["state"]
	//            block=palette[state]
	//            if "id" in nbt.keys():
	//                _id=nbt["id"]
	//                if _id=="minecraft:jigsaw":
	//                    orientation="null"
	//                    if "Properties" in block:
	//                        properties=block["Properties"]
	//                        if "orientation" in properties:
	//                            orientation='"'+properties["orientation"]+'"'
	//                    if nbt['name']!=nbt['target']:
	//                        print("ERROR",nbt['target'],nbt['name'])
	//                        sys.exit(1)
	//                    l.append(f"\tnew Pair<>(new Quad<>(\"{nbt['pool'].replace('minecraft:','')}\", \"{nbt['name'].replace('minecraft:','')}\", {orientation}, \"{nbt['final_state'].replace('minecraft:','')}\"), new BPos({int(pos[0])},{int(pos[1])},{int(pos[2])}))")
	//    if len(l)==0:
	//        print("Collections.emptyList(")
	//    elif len(l)==1:
	//        print("Collections.singletonList(")
	//    else:
	//        print("Arrays.asList(")
	//    for i in range(len(l)):
	//        if i!=len(l)-1:
	//            print(l[i]+",")
	//        else:
	//            print(l[i])
	//
	//    print(f'));')
	//
	//
	public static final HashMap<String, List<Pair<Quad<String, String, String, String>, BPos>>> JIGSAW_BLOCKS = new HashMap<String, List<Pair<Quad<String, String, String, String>, BPos>>>() {{
		this.put("common/iron_golem", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/well_bottom", Collections.singletonList(
			new Pair<>(new Quad<>("village/common/well_bottoms", "bottom", "up_north", "cobblestone"), new BPos(3, 2, 0))
		));
		this.put("common/animals/cat_black", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cat_british", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cat_calico", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cat_jellie", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cat_persia", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cat_ragdoll", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cat_red", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cat_siamese", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cat_tabby", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cat_white", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/cows_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/horses_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/horses_2", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/horses_3", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/horses_4", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "hay_block"), new BPos(0, 0, 0))
		));
		this.put("common/animals/horses_5", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/pigs_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/sheep_1", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("common/animals/sheep_2", Collections.singletonList(
			new Pair<>(new Quad<>("empty", "bottom", "down_south", "structure_void"), new BPos(0, 0, 0))
		));
		this.put("decays/grass_11x13", Collections.emptyList(
		));
		this.put("decays/grass_16x16", Collections.emptyList(
		));
		this.put("decays/grass_9x9", Collections.emptyList(
		));
	}};
}
