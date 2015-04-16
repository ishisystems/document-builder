package com.craigburke.document.core.factory

import com.craigburke.document.core.TextBlock
import com.craigburke.document.core.Text
import com.craigburke.document.core.Row
import com.craigburke.document.core.Column

/**
 * Factory for column nodes
 * @author Craig Burke
 */
class ColumnFactory extends AbstractFactory {

	boolean isLeaf() { false }
    boolean onHandleNodeAttributes(FactoryBuilderSupport builder, node, Map attributes) { false }

	def newInstance(FactoryBuilderSupport builder, name, value, Map attributes) {
		Column column = new Column(attributes)
		Row row = builder.current
		column.parent = row
		builder.setNodeProperties(column, attributes, 'column')

		if (value) {
			TextBlock paragraph = builder.getColumnParagraph(column)
			List elements = paragraph.addText(value.toString())
			elements.each { node ->
				if (node instanceof Text) {
					builder.setNodeProperties(node, [:], 'text')
				}
			}
		}

		column
	}

}