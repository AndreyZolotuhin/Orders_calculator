package ru.azolotuhin.calculator.Metal.Model;

import ru.azolotuhin.calculator.Metal.MetalConstants;

public class MetalChannel extends MetalElement {

    public String getChannelNumber() {
        return channelNumber;
    }

    private String channelNumber;

    public MetalChannel(int id, String channelNumber, MetalConstants.steelType steelType, double metalWeight, double metalSquare, String standart) {
        super(id,MetalConstants.metalType.CHANNEL, steelType, metalWeight, metalSquare,standart);
        this.channelNumber = channelNumber;
    }
}

