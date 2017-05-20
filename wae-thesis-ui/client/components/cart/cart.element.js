import React from 'react'
import { ListItem, Avatar, IconButton } from 'material-ui'
import Delete from 'material-ui/svg-icons/action/delete-forever'

export class CartElement extends React.Component {

  deleteProduct() {
    this.props.deleteProductCart(this.props.id)
  }

  render() {
    let { productName, productPrice, productUrl } = this.props.product
    return (
      <ListItem
        leftAvatar={<Avatar src={productUrl}/>}
        primaryText={productName}
        secondaryText={<p>${productPrice}</p>}
        rightIconButton={
          <IconButton
            tooltip={`Delete ${productName} ?`}
            iconStyle={{color: "#252f3e"}}
            tooltipPosition="top-left"
            touch={true}
            onTouchTap={() => this.deleteProduct()}
          >
            <Delete />
          </IconButton>
        }
      />
    )
  }
}

CartElement.propTypes = {
  id: React.PropTypes.string.isRequired,
  product: React.PropTypes.object.isRequired,
  deleteProductCart: React.PropTypes.func.isRequired
}