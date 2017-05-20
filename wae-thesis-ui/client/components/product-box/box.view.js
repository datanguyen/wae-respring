import React from 'react'
import cssModules from 'react-css-modules'
import style from './box.style.scss'
import { BoxDetails } from './box.details'

@cssModules(style, {errorWhenNotFound: false})
export class ProductBoxView extends React.Component {
  render() {
    return (
      <section className="animated flash" styleName="product-box">
        {
          this.props.products.map(product => {
            return (
              <article styleName="product-box_box">
                <h1 styleName="vertical-article__header">
                  {product.productName}
                </h1>
                <p styleName="vertical-article__content--main">
                  <img src={product.productImgUrl} style={{width: '100%', height: '300px', maxWidth: '300px'}}/>
                </p>
                <BoxDetails productName={product.productName}
                            productPrice={product.productPrice}
                            productUrl={product.productImgUrl}
                            addProductCart={this.props.addProductCart}
                />
              </article>
            )
          })
        }
      </section>
    )
  }
}

ProductBoxView.propTypes = {
  products: React.PropTypes.array.isRequired,
  addProductCart: React.PropTypes.func.isRequired
}