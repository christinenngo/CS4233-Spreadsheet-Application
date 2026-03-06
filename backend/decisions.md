# Design Decisions
Milestone 3
Christine Ngo

> From milestone 2, the composite pattern was used to implement cells, cell groups, and cell components for the cell references. I didn't need to make any changes to this design for milestone 3.
> 
> Added a getReferencedCells method to the Expression interface to allow cells to get a set of cells they reference. This helped implement the observer pattern, since it lets cells know which cells they need to observe or are observed by.
> 
> Used the observer pattern to implement the cell's ability to notify its observers when it changes and automatically update the values. Cell implements both the subject and observer interfaces because it can be observed by other cells, and it can also observe other cells to update based on their changes. Cell group also implements both because it can be referred to by other cells, and it also observes the cells in its group to update based on their changes.
> 
> I refactored OperatorExpression into separate subclasses for each type of operator (arithmetic, aggregate, and unary) instead of having all operators together. This allowed for better organization and separation of the different operator types. 
>
> Added factories for each operator type and a factory producer to create the appropriate factory needed for the certain operator. This allows for easier creation of operator expressions, follows the factory design pattern more closely, and makes it easier to add new operator types in the future without modifying existing code.
> 
> Extended the AbstractExpression class to add support for unary methods, which didn't require any changes to the existing code except the parser since it's a subclass. This allowed for better organization and separation of unary operators from the other operator types.
> 
> For the new aggregate operators (min, max, and median), I only needed to extend AggregateOperatorExpression and implement each operator's logic in evaluate. I didn't need to modify any existing code and only added to the factory for aggregate operators to support the new operators.
