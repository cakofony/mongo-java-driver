/*
 * Copyright (c) 2008 - 2013 10gen, Inc. <http://10gen.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mongodb;

import org.bson.BSONBinaryReader;
import org.bson.BSONBinaryWriter;
import org.bson.io.BasicOutputBuffer;
import org.bson.io.ByteBufferInput;
import org.mongodb.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * A simple wrapper around a byte array that is the representation of a single BSON document.
 */
// TODO: Add an easy way to iterate over the fields?
// TODO: Should this be in the bson module?
public class BsonDocumentBuffer {
    private final byte[] bytes;

    /**
     * Constructs a new instance with the given byte array.  Note that it does not make a copy
     * of the array, so do not modify it after passing it to this constructor.
     *
     * @param bytes the bytes representing a BSON document.
     */
    public BsonDocumentBuffer(final byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes can not be null");
        }
        this.bytes = bytes;
    }

    /**
     * Construct a new instance from the given document and serializer for the document type.
     *
     * @param document the document to transform
     * @param serializer the serializer to facilitate the transformation
     */
    public <T> BsonDocumentBuffer(final T document, final Serializer<T> serializer) {
        BSONBinaryWriter writer = new BSONBinaryWriter(new BasicOutputBuffer());
        serializer.serialize(writer, document);
        this.bytes = writer.getBuffer().toByteArray();
    }

    /**
     * Returns a ByteBuffer that wraps the byte array, withe the proper byte order.  Any changes
     * made to this ByteBuffer will be reflected in the underlying byte array owned by this instance.
     *
     * @return a byte buffer that wraps the byte array owned by this instance.
     */
    public ByteBuffer getByteBuffer() {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        return buffer;
    }

    /**
     * Deserialize this into a document.
     *
     * @param serializer the serializer to facilitate the transformation
     * @return the deserialized document
     */
    public <T> T deserialize(final Serializer<T> serializer) {
        return serializer.deserialize(new BSONBinaryReader(new ByteBufferInput(getByteBuffer())));
    }
}
