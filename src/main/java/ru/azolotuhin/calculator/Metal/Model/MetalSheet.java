package ru.azolotuhin.calculator.Metal.Model;

import ru.azolotuhin.calculator.Metal.MetalConstants;

public class MetalSheet extends MetalElement {

    private String sheetThickness;

    public String getSheetThickness() {
        return sheetThickness;
    }

    public MetalSheet(int id, String sheetThickness, MetalConstants.steelType steelType, double metalWeight, String standard) {
        super(id, MetalConstants.metalType.SHEET, steelType, metalWeight, 1,standard);
        this.sheetThickness = sheetThickness;
    }
}
