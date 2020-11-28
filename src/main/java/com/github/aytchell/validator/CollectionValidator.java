package com.github.aytchell.validator;

import com.github.aytchell.validator.exceptions.ValidationException;

/**
 * Base for all validators of classes which behaves "collection-like" (List, Set)
 *
 * @param <TYPE> the type of the entries in the collection under test
 * @param <VALIDATOR> the type of the validator specific for the actual collection under test
 */
public interface CollectionValidator<TYPE, VALIDATOR> extends ContainerValidator<VALIDATOR> {
    /**
     * Check that a given entry is contained in the collection under test
     *
     * @param key this entry is expected to be contained in the collection
     * @return the CollectionValidator itself so calls can be chained
     * @throws ValidationException in case the collection under test does <i>not</i> contain {@code key}
     */
    VALIDATOR contains(TYPE key) throws ValidationException;

    /**
     * Check that a given entry is <i>not</i> contained in the collection under test
     *
     * @param key this entry is expected to be absent from the collection
     * @return the CollectionValidator itself so calls can be chained
     * @throws ValidationException in case the collection under test <i>does</i> contain {@code key}
     */
    VALIDATOR containsNot(TYPE key) throws ValidationException;

    /**
     * Check all entries of the collection while treating them as numerical values
     * <p>
     * Unfortunately this feature hits the borders of Java generic's possibilities. There is no way to find the correct
     * validator for the {@code TYPE} of the entries at compile-time. So there's a bit help needed from the user of this
     * library:
     * <ul>
     *     <li>if the collection under test contains numerical values (i.e. Short, Integer or Long) then one should use
     *     this method.</li>
     *     <li>if the collection contains strings, see {@link CollectionValidator#eachStringEntry}</li>
     *     <li>otherwise see {@link CollectionValidator#eachCustomEntry}</li>
     * </ul>
     * <p>
     * This method will visit all entries of the collection and apply a given consumer to it (that should validate the
     * entry).
     * <p>
     * Usage example:
     * <pre>
     Validator.expect(integerList)
            .notNull()
            .eachNumericEntry(
                    v -&gt; v.notNull().greaterEqThan(24));
     * </pre>
     *
     * @param entryValidator a function similar to a Consumer which is applied to each entry of the collection and
     *         throws if a value somehow fails the check
     * @return the CollectionValidator itself so calls can be chained
     * @throws ValidationException in case the given validator fails for one of the entries
     * @throws ClassCastException in case the collection does not contain numerical types
     * @see LongValidator
     */
    VALIDATOR eachNumericEntry(LongEntryValidator entryValidator) throws ValidationException;

    /**
     * Check all entries of the collection while treating them as {@link String}s
     * <p>
     * Unfortunately this feature hits the borders of Java generic's possibilities. There is no way to find the correct
     * validator for the {@code TYPE} of the entries at compile-time. So there's a bit help needed from the user of this
     * library:
     * <ul>
     *     <li>if the collection contains Strings then one should use this method
     *     <li>if the collection under test contains numerical values (i.e. Short, Integer or Long) then
     *     see {@link CollectionValidator#eachNumericEntry}</li>
     *     <li>otherwise see {@link CollectionValidator#eachCustomEntry}</li>
     * </ul>
     * <p>
     * This method will visit all entries of the collection and apply a given consumer to it (that should validate the
     * entry).
     * <p>
     * Usage example:
     * <pre>
     Validator.expect(filledList, "filledList")
            .ifNotNull()
            .eachStringEntry(
                    v -&gt; v.notNull().lengthAtMost(20));
     * </pre>
     *
     * @param entryValidator a function similar to a Consumer which is applied to each entry of the collection and
     *         throws if a value somehow fails the check
     * @return the CollectionValidator itself so calls can be chained
     * @throws ValidationException in case the given validator fails for one of the entries
     * @throws ClassCastException in case the collection does not contain Strings
     * @see StringValidator
     */
    VALIDATOR eachStringEntry(StringEntryValidator entryValidator) throws ValidationException;

    /**
     * Check all entries of the collection while treating them as 'something but not numeric not string'
     * <p>
     * Unfortunately this feature hits the borders of Java generic's possibilities. There is no way to find the correct
     * validator for the {@code TYPE} of the entries at compile-time. So there's a bit help needed from the user of this
     * library:
     * <ul>
     *     <li>if the collection under test contains numerical values (i.e. Short, Integer or Long) then one should use
     *     {@link CollectionValidator#eachNumericEntry}.</li>
     *     <li>if the collection contains strings, see {@link CollectionValidator#eachStringEntry}</li>
     *     <li>otherwise this is the correct method to use</li>
     * </ul>
     * <p>
     * This method will visit all entries of the collection and apply a given consumer to it (that should validate the
     * entry).
     * <p>
     * Usage example:
     * <pre>
     Validator.expect(thingies, "thingies").notNull().eachCustomEntry(
            e -&gt; {
                   Validator.expect(e.getName(), "name").notNull().notBlank();
                   Validator.expect(e.getValue(), "value").notNull().greaterThan(0);
            });
     * </pre>
     *
     * @param entryValidator a function similar to a Consumer which is applied to each entry of the collection and
     *         throws if a value somehow fails the check
     * @return the CollectionValidator itself so calls can be chained
     * @throws ValidationException in case the given validator fails for one of the entries
     */
    VALIDATOR eachCustomEntry(CustomEntryValidator<TYPE> entryValidator) throws ValidationException;
}
