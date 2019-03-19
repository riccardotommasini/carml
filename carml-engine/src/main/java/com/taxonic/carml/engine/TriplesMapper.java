package com.taxonic.carml.engine;

import com.taxonic.carml.logical_source_resolver.LogicalSourceResolver;
import com.taxonic.carml.model.TriplesMap;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.LinkedHashModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

class TriplesMapper<T> {

    private static final Logger LOG = LoggerFactory.getLogger(TriplesMapper.class);
    private final Set<PredicateObjectMapper> predicateObjectMappers;

    private String name;
    private Supplier<Iterable<T>> getIterator;
    private LogicalSourceResolver.ExpressionEvaluatorFactory<T> expressionEvaluatorFactory;
    private SubjectMapper subjectMapper;

    TriplesMapper(
            TriplesMapperComponents<T> trMapperComponents,
            SubjectMapper subjectMapper
    ) {
        this(
                trMapperComponents.getName(),
                trMapperComponents.getIterator(),
                trMapperComponents.getExpressionEvaluatorFactory(),
                subjectMapper
        );
    }

    TriplesMapper(
            String name,
            Supplier<Iterable<T>> getIterator,
            LogicalSourceResolver.ExpressionEvaluatorFactory<T> expressionEvaluatorFactory,
            SubjectMapper subjectMapper
    ) {
        this.name = name;
        this.getIterator = getIterator;
        this.expressionEvaluatorFactory = expressionEvaluatorFactory;
        this.subjectMapper = subjectMapper;
        this.predicateObjectMappers = this.subjectMapper.getPredicateObjectMappers();
    }

    List<Model> map(Set<TriplesMapper> objectmaps) {
        List<Model> models = new ArrayList<>();
        LOG.debug("Executing TriplesMap {} ...", name);
        Iterable<T> iter = getIterator.get();
        iter.forEach(e -> models.add(map(e, objectmaps, new LinkedHashModel())));
        return models;
    }


    void map(Model model) {
        LOG.debug("Executing TriplesMap {} ...", name);
        Iterable<T> iter = getIterator.get();
        iter.forEach(e -> map(e, model));
    }


    private Model map(T entry, Set<TriplesMapper> om, Model model) {
        LOG.trace("Mapping triples for entry {}", entry);
        EvaluateExpression evaluate =
                expressionEvaluatorFactory.apply(entry);
        subjectMapper.map(model, evaluate);
        om.forEach(tmo -> tmo.map(entry, model));
        return model;
    }

    private Model map(T entry, Model model) {
        LOG.trace("Mapping triples for entry {}", entry);
        EvaluateExpression evaluate =
                expressionEvaluatorFactory.apply(entry);
        subjectMapper.map(model, evaluate);
        return model;
    }
}
