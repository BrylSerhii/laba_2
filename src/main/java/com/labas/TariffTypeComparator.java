package com.labas;

import generated.TariffType;

import java.util.Comparator;

public class TariffTypeComparator implements Comparator<TariffType> {
    @Override
    public int compare(TariffType f1, TariffType f2) {
        return f1.getName().compareTo(f2.getName());
    }
}
