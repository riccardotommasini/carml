package com.taxonic.carml.util;

import com.taxonic.carml.model.NameableStream;
import com.taxonic.carml.model.impl.CarmlResource;
import com.taxonic.carml.rdf_mapper.annotations.RdfProperty;
import com.taxonic.carml.vocab.Carml;
import com.taxonic.carml.vocab.Vocals;
import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Objects;

public class VocalsWebStream extends CarmlResource implements WebStream {

    private String streamName;

    public VocalsWebStream() {
    }

    public VocalsWebStream(String streamName) {
        this.streamName = streamName;
    }

    @RdfProperty(Vocals.streamName)
    @Override
    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VocalsWebStream) {
            VocalsWebStream other = (VocalsWebStream) obj;
            return Objects.equals(streamName, other.getStreamName());
        }
        return false;
    }

    @Override
    public String toString() {
        return new ReflectionToStringBuilder(this, new MultilineRecursiveToStringStyle()).toString();
    }

}
