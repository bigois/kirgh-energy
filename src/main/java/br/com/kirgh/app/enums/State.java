package br.com.kirgh.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This is an enum class that represents the states of Brazil.
 */
@AllArgsConstructor
@Getter
public enum State {
    /**
     * {@code AC} is defining a value for the state of Acre in Brazil.
     */
    AC("Acre"),

    /**
     * {@code AL} is defining a value for the state of Alagoas in Brazil.
     */
    AL("Alagoas"),

    /**
     * {@code AP} is defining a value for the state of Amapá in Brazil.
     */
    AP("Amapá"),

    /**
     * {@code AM} is defining a value for the state of Amazonas in Brazil.
     */
    AM("Amazonas"),

    /**
     * {@code BA} is defining a value for the state of Bahia in Brazil.
     */
    BA("Bahia"),

    /**
     * {@code CE} is defining a value for the state of Ceará in Brazil.
     */
    CE("Ceará"),

    /**
     * {@code DF} is defining a value for the state of Distrito Federal in Brazil.
     */
    DF("Distrito Federal"),

    /**
     * {@code ES} is defining a value for the state of Espírito Santo in Brazil.
     */
    ES("Espírito Santo"),

    /**
     * {@code GO} is defining a value for the state of Goiás in Brazil.
     */
    GO("Goiás"),

    /**
     * {@code MA} is defining a value for the state of Maranhão in Brazil.
     */
    MA("Maranhão"),

    /**
     * {@code MT} is defining a value for the state of Mato Grosso in Brazil.
     */
    MT("Mato Grosso"),

    /**
     * {@code MS} is defining a value for the state of Mato Grosso do Sul in Brazil.
     */
    MS("Mato Grosso do Sul"),

    /**
     * {@code MG} is defining a value for the state of Minas Gerais in Brazil.
     */
    MG("Minas Gerais"),

    /**
     * {@code PA} is defining a value for the state of Pará in Brazil.
     */
    PA("Pará"),

    /**
     * {@code PB} is defining a value for the state of Paraíba in Brazil.
     */
    PB("Paraíba"),

    /**
     * {@code PR} is defining a value for the state of Paraná in Brazil.
     */
    PR("Paraná"),

    /**
     * {@code PE} is defining a value for the state of Pernambuco in Brazil.
     */
    PE("Pernambuco"),

    /**
     * {@code PI} is defining a value for the state of Piauí in Brazil.
     */
    PI("Piauí"),

    /**
     * {@code RJ} is defining a value for the state of Rio de Janeiro in Brazil.
     */
    RJ("Rio de Janeiro"),

    /**
     * {@code RN} is defining a value for the state of Rio Grande do Norte in Brazil.
     */
    RN("Rio Grande do Norte"),

    /**
     * {@code RS} is defining a value for the state of Rio Grande do Sul in Brazil.
     */
    RS("Rio Grande do Sul"),

    /**
     * {@code RO} is defining a value for the state of Rondônia in Brazil.
     */
    RO("Rondônia"),

    /**
     * {@code RR} is defining a value for the state of Roraima in Brazil.
     */
    RR("Roraima"),

    /**
     * {@code SC} is defining a value for the state of Santa Catarina in Brazil.
     */
    SC("Santa Catarina"),

    /**
     * {@code SP} is defining a value for the state of São Paulo in Brazil.
     */
    SP("São Paulo"),

    /**
     * {@code SE} is defining a value for the state of Sergipe in Brazil.
     */
    SE("Sergipe"),

    /**
     * {@code TO} is defining a value for the state of Tocantins in Brazil.
     */
    TO("Tocantins");

    private final String stateName;
}
