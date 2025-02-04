Task description:

Consider this description as a business task description in issue tracking system (e.g. Jira).
All the analysis was done by system analysts and following description was created.

Insurance company wants to start issuing private property policies to their customers.
System analysts found out that there will be a policy which will have objects (e.g. a House) and that objects
will have sub-objects (e.g. electronic devices such as TV).
One policy can contain multiple objects. One object can contain multiple sub-objects.
In this iteration, customer needs a functionality that calculates premium (a price that will be paid by each
client that will buy this insurance) for the policy.

Premium is calculated by a formula defined in "Needed functionality" section.
In short - formula groups all sub-objects by their type, sums their sum-insured and applies coefficient to the
sum. Then all group sums are summed up which gets us a premium that must be paid by the client.

No GUI is needed, policy data will be sent through the API directly to the methods that will be created.
No database is needed, functionality should not store any data. It should receive policy object, calculate
premium and return result.
