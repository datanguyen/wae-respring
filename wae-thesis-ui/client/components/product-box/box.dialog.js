import React from 'react'
import {Col, Row} from 'react-bootstrap'

export class ProductDialog extends React.Component {
  render() {
    let {productName, productUrl, productPrice} = this.props
    return (
      <div style={{color: "black", fontSize: "25px"}}>
        <Row>
          <Col md={4}>
            <div style={{fontWeight: "bolder"}}>{ productName }</div>
            <div>Price: ${ productPrice }</div>
          </Col>
          <Col md={8}>
            <img src={productUrl}
                 alt={productName}
                 style={{
                   width: '100%',
                   height: '300px',
                   maxWidth: '300px',
                   border: '1px solid #ccc'
                 }}
            />
          </Col>
        </Row>
      </div>
    )
  }
}

ProductDialog.propTypes = {
  productName: React.PropTypes.string.isRequired,
  productPrice: React.PropTypes.number.isRequired,
  productUrl: React.PropTypes.string.isRequired
}