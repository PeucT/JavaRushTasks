package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

/**
 * Created by Archmage on 28.07.2017.
 */
public class FactoryProducer {
    public enum HumanFactoryType{
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory(HumanFactoryType type){
        switch (type){
            case MALE:
                return new MaleFactory();

            case FEMALE:
                return new FemaleFactory();

        }
        return null;
    }
}
