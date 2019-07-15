package ru.azolotuhin.calculator.Metal;

public class MetalConstants {

    public enum metalType{
        IBEAM("Двутавр"),
        SHEET("Лист"),
        SQUARETUBE("Труба профильная"),
        CIRCLETUBE("Труба круглая"),
        CORNER("Уголок"),
        CHANNEL("Швеллер");

        private String name;
        metalType(String name){
            this.name = name;
        }
        public String getName(){ return name;}
    }

    public enum steelType{
        C245("C245"),
        C345("C345"),
        C390("C390"),
        HSND_10("10ХСНД"),
        HSND_15("15ХСНД");

        private String name;
        steelType(String name){
            this.name = name;
        }
        public String getName(){ return name;}
    }
}
