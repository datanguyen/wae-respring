import React from 'react'
import { MuiThemeProvider, RaisedButton, Badge} from 'material-ui'
import cssModules from 'react-css-modules'
import style from './box.style.scss'

@cssModules(style, {errorWhenNotFound: false})
export class ProductBox extends React.Component {
  render() {
    return (
      <section className="animated flash" styleName="product-box">
        {
          this.props.products.map(product => {
            return (
              <article styleName="product-box_box">
                <h1 styleName="vertical-article__header">
                  {product.productName}
                  <MuiThemeProvider>
                    <Badge badgeContent="&copy;"
                           primary={true}
                           badgeStyle={{color: "black"}}
                    />
                  </MuiThemeProvider>
                  </h1>
                <p styleName="vertical-article__content--main">
                  <img src={product.productImgUrl} style={{width: '100%', height: '300px', maxWidth: '300px'}}/>
                </p>
                <MuiThemeProvider>
                  <RaisedButton label="Sign In"
                                labelPosition="before"
                                backgroundColor="#252f3e"
                                labelColor="white"
                                className="anoymynouse"
                                buttonStyle={{width: "50px"}}
                                style={{margin: "40px 0 10px 0"}}
                  />
                </MuiThemeProvider>
              </article>
            )
          })
        }
      </section>
    )
  }
}

ProductBox.propTypes = {
  products: React.PropTypes.array.isRequired
}