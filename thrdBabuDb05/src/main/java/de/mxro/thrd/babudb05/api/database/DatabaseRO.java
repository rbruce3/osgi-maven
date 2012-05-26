/*
 * Copyright (c) 2009-2011, Jan Stender, Bjoern Kolbeck, Mikael Hoegqvist,
 *                     Felix Hupfeld, Felix Langner, Zuse Institute Berlin
 * 
 * Licensed under the BSD License, see LICENSE file for details.
 * 
 */

package de.mxro.thrd.babudb05.api.database;

import de.mxro.thrd.babudb05.api.exception.BabuDBException;

/**
 * This interface contains read-only methods on a database.
 * 
 * @author stenjan
 *
 */
public interface DatabaseRO {
    
    /**
     * Performs a lookup for a single key. The result object contains a byte
     * array containing the value, or <code>null</code> if the key could not be
     * found.
     * 
     * @param indexId
     *            index id (0..NumIndices-1)
     * @param key
     *            the key to look up
     * @param context
     *            arbitrary context which is passed to the listener.
     * @return a future as proxy for the request result.
     */
    public DatabaseRequestResult<byte[]> lookup(int indexId, byte[] key, 
            Object context);
    
    /**
     * Executes a prefix lookup. The result object contains an iterator to the
     * database starting at the first matching key and returning key/value pairs
     * in ascending order.
     * 
     * @param indexId
     *            index id (0..NumIndices-1)
     * @param key
     *            the key to start the iterator at
     * @param context
     *            arbitrary context which is passed to the listener.
     * @return a future as proxy for the request result.
     */
    public DatabaseRequestResult<ResultSet<byte[], byte[]>> prefixLookup(
            int indexId, byte[] key, Object context);
    
    /**
     * Executes a prefix lookup. The result object contains an iterator to the
     * database starting at the last matching key and returning key/value pairs
     * in descending order.
     * 
     * @param indexId
     *            index id (0..NumIndices-1)
     * @param key
     *            the key to start the iterator at
     * @param context
     *            arbitrary context which is passed to the listener.
     * @return a future as proxy for the request result.
     */
    public DatabaseRequestResult<ResultSet<byte[], byte[]>> 
            reversePrefixLookup(int indexId, byte[] key, Object context);
    
    /**
     * Executes a range lookup. The result object contains an iterator to the
     * database starting at the first key greater or equal to <code>from</code>
     * and returning key/value pairs in ascending order.
     * <p>
     * Note that <code>from</code> needs to be smaller than or equal to
     * <code>to</code> according to the comparator associated with the index.
     * </p>
     * 
     * @param indexId
     *            index id (0..NumIndices-1)
     * @param from
     *            the key to start the iterator at (inclusively)
     * @param to
     *            the key to end the iterator at (exclusively)
     * @param context
     *            arbitrary context which is passed to the listener.
     * @return a future as proxy for the request result.
     */
    public DatabaseRequestResult<ResultSet<byte[], byte[]>> rangeLookup(
            int indexId, byte[] from, byte[] to, Object context);
    
    /**
     * Executes a range lookup. The result object contains an iterator to the
     * database starting at the first key less or equal to <code>to</code> and
     * returning key/value pairs in descending order.
     * <p>
     * Note that <code>from</code> needs to be greater than or equal to
     * <code>to</code> according to the comparator associated with the index.
     * </p>
     * 
     * @param indexId
     *            index id (0..NumIndices-1)
     * @param from
     *            the key to start the iterator at (inclusively)
     * @param to
     *            the key to end the iterator at (exclusively)
     * @param context
     *            arbitrary context which is passed to the listener.
     * @return a future as proxy for the request result.
     */
    public DatabaseRequestResult<ResultSet<byte[], byte[]>> 
            reverseRangeLookup(int indexId, byte[] from, byte[] to, 
                    Object context);
    
    /**
     * <p>
     * Performs a user-defined lookup. Return value will contain the result
     * generated by the user defined lookup method.
     * </p>
     * 
     * @param udl
     *            the method to be executed
     * @param context
     *            arbitrary context which is passed to the listener.
     * @return a future as proxy for the request result.
     */
    public DatabaseRequestResult<Object> userDefinedLookup(UserDefinedLookup udl, 
            Object context);
    
    /**
     * Shuts down the database.
     * 
     * @attention: does not create a final checkpoint!
     */
    public void shutdown() throws BabuDBException;
    
}
