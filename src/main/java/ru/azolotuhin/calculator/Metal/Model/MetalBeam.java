package ru.azolotuhin.calculator.Metal.Model;

import ru.azolotuhin.calculator.Metal.MetalConstants;

public class MetalBeam extends MetalElement {

    public String getBeamNumber() {
        return beamNumber;
    }

    private String beamNumber;

    public MetalBeam(int id, String beamNumber, MetalConstants.steelType steelType, double metalWeight, double metalSquare, String standart) {
        super(id,MetalConstants.metalType.IBEAM, steelType, metalWeight, metalSquare, standart);
        this.beamNumber = beamNumber;

    }
}
