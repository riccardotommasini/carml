package com.taxonic.carml.model.impl;

import com.taxonic.carml.model.TermMap;
import com.taxonic.carml.model.TermType;
import com.taxonic.carml.model.TriplesMap;
import com.taxonic.carml.rdf_mapper.annotations.RdfProperty;
import com.taxonic.carml.rdf_mapper.annotations.RdfType;
import com.taxonic.carml.vocab.Fnml;
import com.taxonic.carml.vocab.Rml;
import com.taxonic.carml.vocab.Rr;
import java.util.Objects;
import org.eclipse.rdf4j.model.Value;

abstract public class CarmlTermMap extends CarmlResource implements TermMap {

	String reference;
	String inverseExpression;
	String template;
	TermType termType;
	Value constant;
	TriplesMap functionValue;

	public CarmlTermMap() {}

	public CarmlTermMap(
		String reference,
		String inverseExpression,
		String template,
		TermType termType,
		Value constant,
		TriplesMap functionValue
	) {
		this.reference = reference;
		this.inverseExpression = inverseExpression;
		this.template = template;
		this.termType = termType;
		this.constant = constant;
		this.functionValue = functionValue;
	}

	@RdfProperty(Rml.reference)
	@Override
	public String getReference() {
		return reference;
	}

	@RdfProperty(Rr.inverseExpression)
	@Override
	public String getInverseExpression() {
		return inverseExpression;
	}

	@RdfProperty(Rr.template)
	@Override
	public String getTemplate() {
		return template;
	}

	// TODO https://www.w3.org/TR/r2rml/#dfn-term-type
	@RdfProperty(Rr.termType)
	@Override
	public TermType getTermType() {
		return termType;
	}

	@RdfProperty(Rr.constant)
	@Override
	public Value getConstant() {
		return constant;
	}

	@RdfProperty(Fnml.functionValue)
	@RdfType(CarmlTriplesMap.class)
	@Override
	public TriplesMap getFunctionValue() {
		return functionValue;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public void setInverseExpression(String inverseExpression) {
		this.inverseExpression = inverseExpression;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public void setTermType(TermType termType) {
		this.termType = termType;
	}

	public void setConstant(Value constant) {
		this.constant = constant;
	}

	public void setFunctionValue(TriplesMap functionValue) {
		this.functionValue = functionValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference, inverseExpression, template, termType, constant, functionValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CarmlTermMap other = (CarmlTermMap) obj;
		return Objects.equals(reference, other.reference) &&
				Objects.equals(inverseExpression, other.inverseExpression) &&
				Objects.equals(template, other.template) &&
				Objects.equals(termType, other.termType) &&
				Objects.equals(constant, other.constant) &&
				Objects.equals(functionValue, other.functionValue);
	}

	public static class Builder {

		String reference;
		String inverseExpression;
		String template;
		TermType termType;
		Value constant;
		TriplesMap functionValue;

		Builder reference(String reference) {
			this.reference = reference;
			return this;
		}

		Builder inverseExpression(String inverseExpression) {
			this.inverseExpression = inverseExpression;
			return this;
		}

		Builder template(String template) {
			this.template = template;
			return this;
		}

		Builder termType(TermType termType) {
			this.termType = termType;
			return this;
		}

		Builder constant(Value constant) {
			this.constant = constant;
			return this;
		}

		Builder functionValue(TriplesMap functionValue) {
			this.functionValue = functionValue;
			return this;
		}

		String getReference() {
			return reference;
		}

		String getInverseExpression() {
			return inverseExpression;
		}

		String getTemplate() {
			return template;
		}

		TermType getTermType() {
			return termType;
		}

		Value getConstant() {
			return constant;
		}

		TriplesMap getFunctionValue() {
			return functionValue;
		}
	}
}
