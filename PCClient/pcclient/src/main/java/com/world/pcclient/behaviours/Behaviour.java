package com.world.pcclient.behaviours;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Behaviour {
    public final String messagesName;
    public final String name;

    public Set<BehavioursRequirementDetail> requirements;
    public final String description;
    
    private Behaviour(String messagesName, String name, String description,
    Set<BehavioursRequirementDetail> requirements) {
        this.messagesName = messagesName;
        this.name = name;
        this.description = description;
        this.requirements = Collections.unmodifiableSet(requirements);
    }

    public static class BehaviourBuilder {
        private final String messagesName;
        private String name;
        public String description;

        private Set<BehavioursRequirementDetail> requirements = new HashSet<>();

        public BehaviourBuilder(String messagesName) {
            this.messagesName = messagesName;
        }

        public BehaviourBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public BehaviourBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public BehaviourBuilder addRequirement(
                BehavioursRequirementDetail requirement) {
            requirements.add(requirement);
            return this;
        }

        public Behaviour build() {
        if (description == null)
                throw new Error("Some behaviour does not have description.");
            
            if (name == null)
                throw new Error("Some behaviour does not have a name.");
            return new Behaviour(messagesName, name, description, requirements);
        }

        public BehaviourBuilder addRequirement(
                BehavioursRequirement behavioursRequirement,
                String description,
                boolean withConcreteIngredient,
                int amount) {
            requirements.add(new BehavioursRequirementDetail(
                    behavioursRequirement,
                    description,
                    withConcreteIngredient,
                    amount));
            return this;
        }

        public BehaviourBuilder addRequirement(
                BehavioursRequirement behavioursRequirement,
                boolean withConcreteIngredient,
                int amount) {
            requirements.add(new BehavioursRequirementDetail(
                    behavioursRequirement,
                    null,
                    withConcreteIngredient,
                    amount));
            return this;
        }
    }

    public static class BehavioursRequirementDetail {
        public BehavioursRequirement requirement;
        public final String description;
        public final boolean withConcreteIngredient;
        public final int amount;

        public BehavioursRequirementDetail(BehavioursRequirement requirement, String description,
                boolean withConcreteIngredient,
                int amount) {
            this.description = description;
            this.requirement = requirement;
            this.withConcreteIngredient = withConcreteIngredient;
            this.amount = amount;
        }

        public BehavioursRequirementDetail(BehavioursRequirement requirement, String description, int amount) {
            this.description = description;
            this.requirement = requirement;
            this.withConcreteIngredient = false;
            this.amount = amount;
        }
    }
}
