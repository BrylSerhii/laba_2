<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <!-- Main template -->
    <xsl:template match="/Tariffs">
        <GroupedTariffs>
            <!-- Get unique values of OperatorName -->
            <xsl:for-each select="Tariff[not(OperatorName=preceding-sibling::Tariff/OperatorName)]">
                <Group>
                    <OperatorName>
                        <xsl:value-of select="OperatorName"/>
                    </OperatorName>
                    <TariffsByOperator>
                        <!-- Group tariffs by their OperatorName -->
                        <xsl:for-each select="Tariff[OperatorName=current()/OperatorName]">
                            <Tariff>
                                <Name><xsl:value-of select="Name"/></Name>
                                <Payroll currency="{Payroll/@currency}">
                                    <xsl:value-of select="Payroll"/>
                                </Payroll>
                                <CallPrices>
                                    <InsideNetwork currency="{CallPrices/InsideNetwork/@currency}">
                                        <xsl:value-of select="CallPrices/InsideNetwork"/>
                                    </InsideNetwork>
                                    <OutsideNetwork currency="{CallPrices/OutsideNetwork/@currency}">
                                        <xsl:value-of select="CallPrices/OutsideNetwork"/>
                                    </OutsideNetwork>
                                    <Landline currency="{CallPrices/Landline/@currency}">
                                        <xsl:value-of select="CallPrices/Landline"/>
                                    </Landline>
                                </CallPrices>
                                <SMSPrice currency="{SMSPrice/@currency}">
                                    <xsl:value-of select="SMSPrice"/>
                                </SMSPrice>
                                <Parameters>
                                    <FavoriteNumber><xsl:value-of select="Parameters/FavoriteNumber"/></FavoriteNumber>
                                    <Tariffing><xsl:value-of select="Parameters/Tariffing"/></Tariffing>
                                    <ConnectionFee currency="{Parameters/ConnectionFee/@currency}">
                                        <xsl:value-of select="Parameters/ConnectionFee"/>
                                    </ConnectionFee>
                                </Parameters>
                            </Tariff>
                        </xsl:for-each>
                    </TariffsByOperator>
                </Group>
            </xsl:for-each>
        </GroupedTariffs>
    </xsl:template>
</xsl:stylesheet>
