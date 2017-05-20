import React from 'react'
import { ProductBoxView } from './box.view'
import { connect } from 'react-redux'
import { addProductCart } from '../cart'

@connect(state => ({}), { addProductCart })
export class ProductBox extends React.Component {
  render() {
    return (
      <ProductBoxView
        products={this.props.products}
        addProductCart={this.props.addProductCart}
      />
    )
  }
}

ProductBox.propTypes = {
  products: React.PropTypes.array.isRequired,
  addProductCart: React.PropTypes.func.isRequired
}