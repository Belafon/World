package com.example.world.game.visibles.resources;

import java.util.Hashtable;
import java.util.Map;

public class ResourceTypes {
    public static record ResourceType(String name, String description, String[] masses) {
    }

    public static final Map<String, ResourceType> resorceTypes = new Hashtable<>();

    public static void addResourceType(String name, String description, String[] masses) {
        resorceTypes.put(name, new ResourceType(name, description, masses));
    }

    static {
        addResourceType("blueberry", "Sweet and juicy berries that grow on bushes.", new String[] {
                "A handful",
                "Two handfuls",
                "Three handfuls",
                "A basketful",
                "A bushel",
                "A truckload"
        });

        addResourceType("mushrooms", "Fungi with a unique flavor and aroma.", new String[] {
                "Just a few",
                "A handful",
                "A bunch",
                "A mountain of",
                "A whole forest of",
                "More than you can carry"
        });

        addResourceType("treeOak", "A large, sturdy tree that produces wood and acorns.", new String[] {
                "A few acorns",
                "A bucketful of acorns",
                "A small log",
                "A large log",
                "A bundle of branches",
                "A whole tree"
        });

        addResourceType("treePine", "A tall, slender tree that produces wood and pinecones.", new String[] {
                "A few pinecones",
                "A small handful of pinecones",
                "A large handful of pinecones",
                "A basketful of pinecones",
                "A truckload of pinecones",
                "A whole forest of pinecones"
        });

        addResourceType("treeSpruce", "A fragrant tree that produces wood and needles.", new String[] {
                "A few needles",
                "A handful of needles",
                "A small bundle of needles",
                "A large bundle of needles",
                "A truckload of needles",
                "A whole forest of needles"
        });
    }
}
