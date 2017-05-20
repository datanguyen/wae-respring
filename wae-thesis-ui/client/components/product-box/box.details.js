import React from 'react'
import { MuiThemeProvider, RaisedButton, Dialog, Snackbar } from 'material-ui'
import { ProductDialog } from './box.dialog'

export class BoxDetails extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      viewDetailOpen: false,
      addToCartOpen: false,
      addMessage: ''
    }
  }

  handleDetailOpen() {
    this.setState({viewDetailOpen: true})
  }

  handleDetailClose() {
    this.setState({viewDetailOpen: false})
  }

  handleCartOpen() {
    let { productName, productPrice, productUrl} = this.props
    let result = this.props.addProductCart({ productName, productPrice, productUrl })

    if (result.type === 'ADD_FAILED') {
      this.setState({ addToCartOpen: true, addMessage: `${productName} is already added`})
    } else {
      this.setState({ addToCartOpen: true, addMessage: `${productName} is added to your cart`})
    }
  }

  handleCartRequestClose() {
    this.setState({ addToCartOpen: false })
  }

  render() {
    const actions = [
      <MuiThemeProvider>
        <RaisedButton
          label="Ok"
          secondary={true}
          keyboardFocused={true}
          onTouchTap={(e) => this.handleDetailClose(e)}
        />
      </MuiThemeProvider>
    ]
    let { productName, productUrl, productPrice} = this.props

    return (
      <div>
        <MuiThemeProvider>
          <RaisedButton label="Add to Cart"
                        labelPosition="before"
                        backgroundColor="#252f3e"
                        labelColor="white"
                        style={{margin: "10px 0 10px 0"}}
                        onTouchTap={(e) => this.handleCartOpen(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <RaisedButton label="View Detail"
                        labelPosition="before"
                        backgroundColor="#252f3e"
                        labelColor="white"
                        style={{margin: "10px 0 10px 10px"}}
                        onTouchTap={(e) => this.handleDetailOpen(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <Snackbar
            open={this.state.addToCartOpen}
            message={this.state.addMessage}
            autoHideDuration={2000}
            bodyStyle={{backgroundColor: "black"}}
            onRequestClose={(e) => this.handleCartRequestClose(e)}
          />
        </MuiThemeProvider>
        <MuiThemeProvider>
          <Dialog
            actions={actions}
            modal={true}
            open={this.state.viewDetailOpen}
            onRequestClose={(e) => this.handleDetailClose(e)}
            contentStyle={{width: "600px"}}
          >
            <ProductDialog
              productName={productName}
              productPrice={productPrice}
              productUrl={productUrl}
            />
          </Dialog>
        </MuiThemeProvider>
      </div>
    )
  }
}

BoxDetails.propTypes = {
  productName: React.PropTypes.string.isRequired,
  productPrice: React.PropTypes.number.isRequired,
  productUrl: React.PropTypes.string.isRequired,
  addProductCart: React.PropTypes.func.isRequired
}